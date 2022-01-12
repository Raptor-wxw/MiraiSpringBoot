package app.hive.mirai;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JavaPluginMain extends JavaPlugin implements ApplicationRunner {

    public static final JavaPluginMain INSTANCE = new JavaPluginMain();
    public JavaPluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.hive.plugin.mirai", "1.0.0")
                .name("MiraiSpringBoot")
                .info("用SpringBoot方式编写Mirai")
                .author("微信公众号：夜寒信息")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("SpringBoot for Mirai 已启用");
        new SpringApplicationBuilder(JavaPluginMain.class).web(WebApplicationType.NONE).run();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            g.getGroup().sendMessage("SpringBoot已启用");
        });
    }
}
