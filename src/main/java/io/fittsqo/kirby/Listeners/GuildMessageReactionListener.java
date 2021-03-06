package io.fittsqo.kirby.Listeners;

import io.fittsqo.kirby.Database.DBAdapter;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class GuildMessageReactionListener extends ListenerAdapter {

    private final DBAdapter dbAdapter;

    public GuildMessageReactionListener(DBAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    @Override
    public void onGuildMessageReactionAdd(@Nonnull GuildMessageReactionAddEvent event) {
        String reactionId;
        String roleId;
        Role role;

        if (event.getReactionEmote().isEmoji())
            reactionId = ("U+" + Integer.toHexString(event.getReactionEmote().getEmoji().codePointAt(0)));
        else
            reactionId = event.getReactionEmote().getId();

        roleId = dbAdapter.getReactionRole(event.getMessageId(), reactionId);
        if (roleId != null) {
            role = event.getGuild().getRoleById(roleId);
            if (role != null)
                event.getGuild().addRoleToMember(event.getUserId(), (role)).queue();
        }
    }

    @Override
    public void onGuildMessageReactionRemove(@Nonnull GuildMessageReactionRemoveEvent event) {
        String reactionId;
        String roleId;
        Role role;

        if (event.getReactionEmote().isEmoji())
            reactionId = ("U+" + Integer.toHexString(event.getReactionEmote().getEmoji().codePointAt(0)));
        else
            reactionId = event.getReactionEmote().getId();

        roleId = dbAdapter.getReactionRole(event.getMessageId(), reactionId);
        if (roleId != null) {
            role = event.getGuild().getRoleById(roleId);
            if (role != null)
                event.getGuild().removeRoleFromMember(event.getUserId(), (role)).queue();
        }
    }

}
