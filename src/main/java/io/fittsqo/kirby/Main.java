package io.fittsqo.kirby;

import io.fittsqo.kirby.Commands.GuildMessageReceivedListener;
import io.fittsqo.kirby.Listeners.*;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {

        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }

        JDABuilder jda = JDABuilder.createDefault(args[0]);

        jda.disableCache(
                CacheFlag.MEMBER_OVERRIDES,
                CacheFlag.VOICE_STATE,
                CacheFlag.ACTIVITY,
                CacheFlag.ONLINE_STATUS
        );
        jda.setBulkDeleteSplittingEnabled(false);
        jda.setActivity(Activity.watching("the stars"));
        jda.addEventListeners(
                new ReadyListener(),
                new GuildMemberJoinListener(),
                new GuildMessageReactionListener(),
                new GuildMessageReceivedListener(),
                new GuildJoinListener(),
                new GuildMessageDeleteListener(),
                new GuildLeaveListener(),
                new TextChannelDeleteListener()
        );
        jda.enableIntents(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_EMOJIS
        );

        jda.build();
    }

}