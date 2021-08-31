package io.fittsqo.kirby.Commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.EnumSet;

public abstract class SlashCommand {

    private final String name;
    private final String description;
    private final int args;
    private final EnumSet<Permission> permissions;
    private final String usage;

    public SlashCommand() {
        this.name = "";
        this.description = "";
        this.args = 0;
        this.permissions = EnumSet.noneOf(Permission.class);
        this.usage = "";
    }

    public void execute(SlashCommandEvent event) { }

    public String getName() {
        return name;
    }

    public int getArgs() {
        return args;
    }

    public EnumSet<Permission> getPermissions() {
        return permissions;
    }

    public String getDescription() { // mainly for /help
        return description;
    }

    public String getUsage() { // mainly for /help
        return this.usage;
    }
}