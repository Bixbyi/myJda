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
        String token = "NzYzNjYzNTEzODY4NTY2NTQw.X36_EQ.AzbWps5NplvCUZ3B1h5SU18_qQQ";

        // args[0] should be the token
        // We only need 2 intents in this bot. We only respond to messages in guilds and private channels.
        // All other events will be disabled.
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
        TextChannel tc = event.getTextChannel();
        Message msg = event.getMessage();
        if (user.isBot()){
            return;
        }
        if (msg.getContentRaw().charAt(0) == ','){
            String[] content =  msg.getContentRaw().substring(1).split("");
            if (
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
                channel.sendMessage("퐁!") /* > RestAction<Message> */
                        .queue(response /* => Message */ -> {
                            response.editMessageFormat("퐁!: %d ms", System.currentTimeMillis() - time).queue();
                        });
            }
        }
    }
}