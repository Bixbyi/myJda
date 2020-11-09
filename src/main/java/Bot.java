import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter
{
    public static void main(String[] args) throws LoginException
    {
        String token = "token";


        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Bot())
                .setActivity(Activity.playing("Type !ping"))
                .build();
        System.out.print("Login 성공!");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        User user = event.getAuthor();
        //TextChannel tc = event.getTextChannel();
        Message msg = event.getMessage();
        if (user.isBot()){
            return;
        }
        if (msg.getContentRaw().charAt(0) == ',' /*접두사*/){
            String[] content =  msg.getContentRaw().substring(1).split("");
            if (
                    /*명령어 (Ping!)*/
                    content[0].equalsIgnoreCase("ping")
                            || content[0].equalsIgnoreCase("핑")
                            || content[0].equalsIgnoreCase("ㅍ")
                            || content[0].equalsIgnoreCase("p")
                            || content[0].equalsIgnoreCase("v")
                            || content[0].equalsIgnoreCase("ㅔ")
                            || content[0].equalsIgnoreCase("ㅔㅑㅜㅎ")
                            || content[0].equalsIgnoreCase("vld")
            ) {
                MessageChannel channel = event.getChannel();
                long time = System.currentTimeMillis();
                /*대답*/
                channel.sendMessage("퐁!")
                        .queue(response -> {
                            /*수정*/
                            response.editMessageFormat("퐁!: %d ms", System.currentTimeMillis() - time).queue();
                        });
            }
        }
    }
}