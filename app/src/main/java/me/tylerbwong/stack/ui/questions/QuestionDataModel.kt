package me.tylerbwong.stack.ui.questions

import me.tylerbwong.stack.data.model.Question
import me.tylerbwong.stack.ui.utils.DynamicDataModel

class QuestionDataModel(internal val question: Question) : DynamicDataModel() {

    internal val questionTitle = question.title
    internal val answerCount = question.answerCount
    internal val questionBody = question.body
    internal val shareLink = question.shareLink
    internal val questionId = question.questionId
    internal val owner = question.owner
    internal val tags = question.tags

    override fun areItemsThemSame(
        other: DynamicDataModel
    ) = other is QuestionDataModel && other.questionId == questionId

    override fun areContentsTheSame(
        other: DynamicDataModel
    ) = other is QuestionDataModel && other.questionTitle == questionTitle &&
            other.answerCount == answerCount && other.questionBody == questionBody &&
            other.owner == owner

    override fun getViewCreator() = ::QuestionHolder
}
