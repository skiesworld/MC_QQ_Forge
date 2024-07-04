package com.github.theword.mcqq.utils;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.MyHoverEntity;
import com.github.theword.mcqq.returnBody.returnModle.MyHoverItem;
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import java.util.List;

public class ParseJsonToEvent {
    public TextComponentString parseMessages(List<? extends MyBaseComponent> myBaseComponentList) {
        TextComponentString textComponentString = new TextComponentString("");
        for (MyBaseComponent myBaseComponent : myBaseComponentList) {
            TextComponentString tempTextComponentString = parsePerMessageToMultiText(myBaseComponent);
            textComponentString.appendSibling(tempTextComponentString);
        }
        return textComponentString;
    }

    public TextComponentString parsePerMessageToMultiText(MyBaseComponent myBaseComponent) {
        TextComponentString textComponentString = new TextComponentString(myBaseComponent.getText());

        Style style = new Style();
        style.setBold(myBaseComponent.isBold());
        style.setItalic(myBaseComponent.isItalic());
        style.setUnderlined(myBaseComponent.isUnderlined());
        style.setStrikethrough(myBaseComponent.isStrikethrough());
        style.setObfuscated(myBaseComponent.isObfuscated());
        style.setInsertion(myBaseComponent.getInsertion());

        if (myBaseComponent.getColor() != null && !myBaseComponent.getColor().isEmpty()) {
            TextFormatting valueByName = TextFormatting.getValueByName(myBaseComponent.getColor());
            style = style.setColor(valueByName != null ? valueByName : TextFormatting.WHITE);
        } else style = style.setColor(TextFormatting.WHITE);

        if (myBaseComponent instanceof MyTextComponent) {
            MyTextComponent myTextComponent = (MyTextComponent) myBaseComponent;
            if (myTextComponent.getClickEvent() != null) {
                ClickEvent.Action tempAction = ClickEvent.Action.getValueByCanonicalName(myTextComponent.getClickEvent().getAction());
                ClickEvent clickEvent = new ClickEvent(tempAction, myTextComponent.getClickEvent().getValue());
                style = style.setClickEvent(clickEvent);
            }
            if (myTextComponent.getHoverEvent() != null) {
                HoverEvent hoverEvent = null;
                switch (myTextComponent.getHoverEvent().getAction()) {
                    case "show_text":
                        if (myTextComponent.getHoverEvent().getBaseComponentList() != null && !myTextComponent.getHoverEvent().getBaseComponentList().isEmpty()) {
                            TextComponentString tempTextComponentString = parseMessages(myTextComponent.getHoverEvent().getBaseComponentList());
                            hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, tempTextComponentString);
                        }
                        break;
                    case "show_item":
                        MyHoverItem myHoverItem = myTextComponent.getHoverEvent().getItem();
                        Item item = Item.getItemById(myHoverItem.getId());
                        ItemStack itemStack = new ItemStack(item, myHoverItem.getCount());
                        hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ITEM, itemStack.getTextComponent());
                        break;
                    case "show_entity":
                        MyHoverEntity myHoverEntity = myTextComponent.getHoverEvent().getEntity();
                        hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ENTITY, parseMessages(myHoverEntity.getName()));
                        break;
                    default:
                        break;
                }
                if (hoverEvent != null)
                    style = style.setHoverEvent(hoverEvent);
            }
        }
        textComponentString.setStyle(style);
        return textComponentString;
    }
}
