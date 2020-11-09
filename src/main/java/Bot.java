import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.awt.Color;
import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter
{
    public static void main(String[] args) throws LoginException
    {
        String token = "token";


        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Bot())
                .setActivity(Activity.playing("<help"))
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
        if (msg.getContentRaw().charAt(0) == '<' /*접두사*/){
            String[] content =  msg.getContentRaw().substring(1).split("");
            MessageChannel channel = event.getChannel();
            EmbedBuilder eb = new EmbedBuilder();
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

                long time = System.currentTimeMillis();
                /*대답*/
                channel.sendMessage("퐁!")
                        .queue(response -> {
                            /*수정.*/
                            response.editMessageFormat("퐁!: %d ms", System.currentTimeMillis() - time).queue();
                        });

            }
            if (
                    /*명령어 (Help!)*/
                    content[0].equalsIgnoreCase("help")
                            ||content[0].equalsIgnoreCase("도움")
                            ||content[0].equalsIgnoreCase("ㄷ")
                            ||content[0].equalsIgnoreCase("h")
                            ||content[0].equalsIgnoreCase("e")
                            ||content[0].equalsIgnoreCase("ㅗ")
                            ||content[0].equalsIgnoreCase("ㅗ디ㅔ")
                            ||content[0].equalsIgnoreCase("ehdna")
            ){
                eb.setTitle("명령어");
                eb.setColor(0xdb1258);
                eb.setDescription("알라라라");
                eb.addField("<ping", "하는 방법: <ping", false);
                eb.setFooter(user.getName(), user.getAvatarUrl());

                channel.sendMessage(eb.build()).queue();
            }

        }
    }
}