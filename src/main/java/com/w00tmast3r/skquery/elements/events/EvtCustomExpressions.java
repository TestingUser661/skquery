package com.w00tmast3r.skquery.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import com.w00tmast3r.skquery.elements.events.lang.MethodEvent;
import com.w00tmast3r.skquery.elements.expressions.ExprCustomExpression;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EvtCustomExpressions extends SkriptEvent {

    private String execute;

    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        String s = ((Literal<String>) args[0]).getSingle();
        if (!ExprCustomExpression.getEntries().contains(s)) {
            Skript.error(s + " is not a registered custom expression. Not defined in any .skqc files.");
            return false;
        }
        execute = s;
        return true;
    }

    @Override
    public boolean check(Event e) {
        return ((MethodEvent) e).getMatch().equals(execute);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "command logic";
    }
}
