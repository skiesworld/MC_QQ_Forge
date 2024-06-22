package com.github.theword.mcqq.utils;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.MyHoverEntity;
import com.github.theword.mcqq.returnBody.returnModle.MyHoverItem;
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ParseJsonToEvent {
    public MutableComponent parseMessages(List<? extends MyBaseComponent> myBaseComponentList) {
        MutableComponent mutableComponent = MutableComponent.create(new LiteralContents(""));
        for (MyBaseComponent myBaseComponent : myBaseComponentList) {
            MutableComponent tempMutableComponent = parsePerMessageToMultiText(myBaseComponent);
            mutableComponent.append(tempMutableComponent);
        }
        return mutableComponent;
    }

    public MutableComponent parsePerMessageToMultiText(MyBaseComponent myBaseComponent) {
        LiteralContents literalContents = new LiteralContents(myBaseComponent.getText());

        ResourceLocation font = null;
        if (myBaseComponent.getFont() != null) {
            font = new ResourceLocation(myBaseComponent.getFont());
        }

        Style style = Style.EMPTY
                .withBold(myBaseComponent.isBold())
                .withItalic(myBaseComponent.isItalic())
                .withUnderlined(myBaseComponent.isUnderlined())
                .withStrikethrough(myBaseComponent.isStrikethrough())
                .withObfuscated(myBaseComponent.isObfuscated())
                .withInsertion(myBaseComponent.getInsertion())
                .withFont(font);
        if (myBaseComponent.getColor() != null && !myBaseComponent.getColor().isEmpty()) {
            style = style.withColor(TextColor.parseColor(myBaseComponent.getColor()));
        }

        if (myBaseComponent instanceof MyTextComponent myTextComponent) {
            if (myTextComponent.getClickEvent() != null) {
                ClickEvent.Action tempAction = ClickEvent.Action.getByName(myTextComponent.getClickEvent().getAction());
                ClickEvent clickEvent = new ClickEvent(tempAction, myTextComponent.getClickEvent().getValue());
                style = style.withClickEvent(clickEvent);
            }
            if (myTextComponent.getHoverEvent() != null) {
                HoverEvent hoverEvent = null;
                switch (myTextComponent.getHoverEvent().getAction()) {
                    case "show_text" -> {
                        if (myTextComponent.getHoverEvent().getBaseComponentList() != null && !myTextComponent.getHoverEvent().getBaseComponentList().isEmpty()) {
                            MutableComponent textComponent = parseMessages(myTextComponent.getHoverEvent().getBaseComponentList());
                            hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, textComponent);
                        }
                    }
                    case "show_item" -> {
                        MyHoverItem myHoverItem = myTextComponent.getHoverEvent().getItem();
                        Item item = Item.byId(myHoverItem.getId());
                        ItemStack itemStack = new ItemStack(item, myHoverItem.getCount());
                        HoverEvent.ItemStackInfo itemStackInfo = new HoverEvent.ItemStackInfo(itemStack);
                        hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ITEM, itemStackInfo);
                    }
                    case "show_entity" -> {
                        MyHoverEntity myHoverEntity = myTextComponent.getHoverEvent().getEntity();
                        Optional<EntityType<?>> entityType = EntityType.byString(myHoverEntity.getType());
                        if (entityType.isPresent()) {
                            HoverEvent.EntityTooltipInfo entityTooltipInfo = new HoverEvent.EntityTooltipInfo(entityType.get(), UUID.randomUUID(), parseMessages(myHoverEntity.getName()));
                            hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ENTITY, entityTooltipInfo);
                        }
                    }
                    default -> {
                    }
                }
                style = style.withHoverEvent(hoverEvent);
            }
        }
        MutableComponent mutableComponent = MutableComponent.create(literalContents);
        mutableComponent.setStyle(style);

        return mutableComponent;
    }
}
