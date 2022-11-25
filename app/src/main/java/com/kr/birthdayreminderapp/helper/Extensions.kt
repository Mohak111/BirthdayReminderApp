package com.kr.birthdayreminderapp.helper

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setCustomItemDecoration(dividerDrawableId: Int, removeLeftRightPadding: Boolean = false, showDividerForLastItem: Boolean = false) {
    val itemDecorator = DividerItemDecorator(ContextCompat.getDrawable(this.context, dividerDrawableId), removeLeftRightPadding, showDividerForLastItem)
    addItemDecoration(itemDecorator)
}