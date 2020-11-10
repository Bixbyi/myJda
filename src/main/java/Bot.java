import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;


import javax.security.auth.login.LoginException;
import java.util.Random;

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
                eb.setDescription("접두사 : <");
                eb.addField("ping", "하는 방법: <ping", false);
                eb.addField("dice", "하는방법: <dice",false);
                eb.setFooter(user.getName(), user.getAvatarUrl());

                channel.sendMessage(eb.build()).queue();
            }
            if (
                    content[0].equalsIgnoreCase("dice")
                            ||content[0].equalsIgnoreCase("주사위")
                            ||content[0].equalsIgnoreCase("ㅈ")
                            ||content[0].equalsIgnoreCase("d")
                            ||content[0].equalsIgnoreCase("w")
                            ||content[0].equalsIgnoreCase("ㅇ")
                            ||content[0].equalsIgnoreCase("얓ㄷ")
                            ||content[0].equalsIgnoreCase("wntkdnl")
            ){
                Random random = new Random();

                int diceNumber = random.nextInt(6)+1;

                eb.setTitle("빠바바ㅏ바바");
                eb.setColor(0x000000);
                eb.addField("주사위의 값은", ""+diceNumber,false);
                channel.sendMessage(eb.build()).queue();
            }

        }
    }
}