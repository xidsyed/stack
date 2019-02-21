package me.tylerbwong.stack.ui.answers

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.bumptech.glide.request.RequestOptions
import me.tylerbwong.stack.R
import me.tylerbwong.stack.ui.owners.BadgeView
import me.tylerbwong.stack.ui.utils.DynamicViewHolder
import me.tylerbwong.stack.ui.utils.GlideApp
import me.tylerbwong.stack.ui.utils.format
import me.tylerbwong.stack.ui.utils.inflateWithoutAttaching
import me.tylerbwong.stack.ui.utils.setMarkdown
import me.tylerbwong.stack.ui.utils.toHtml

class AnswerHolder(parent: ViewGroup) : DynamicViewHolder(
        parent.inflateWithoutAttaching(R.layout.answer_holder)
) {
    private val acceptedAnswerCheck: ImageView = ViewCompat.requireViewById(itemView, R.id.acceptedAnswerCheck)
    private val votes: TextView = ViewCompat.requireViewById(itemView, R.id.votes)
    private val answerBody: TextView = ViewCompat.requireViewById(itemView, R.id.answerBody)
    private val userImage: ImageView = ViewCompat.requireViewById(itemView, R.id.userImage)
    private val username: TextView = ViewCompat.requireViewById(itemView, R.id.username)
    private val reputation: TextView = ViewCompat.requireViewById(itemView, R.id.reputation)
    private val badgeView: BadgeView = ViewCompat.requireViewById(itemView, R.id.badgeView)

    override fun bind(data: Any) {
        (data as? AnswerDataModel)?.let { dataModel ->
            val answer = dataModel.answer
            val voteCount = answer.upVoteCount - answer.downVoteCount
            votes.text = itemView.context.resources.getQuantityString(R.plurals.votes, voteCount, voteCount)
            acceptedAnswerCheck.visibility = if (answer.isAccepted) View.VISIBLE else View.GONE
            answerBody.setMarkdown(answer.bodyMarkdown)

            username.text = answer.owner.displayName.toHtml()
            GlideApp.with(itemView)
                    .load(answer.owner.profileImage)
                    .placeholder(R.drawable.user_image_placeholder)
                    .apply(RequestOptions.circleCropTransform())
                    .into(userImage)
            userImage.setOnClickListener { dataModel.onProfilePictureClicked(it.context) }
            reputation.text = answer.owner.reputation.toLong().format()
            badgeView.badgeCounts = answer.owner.badgeCounts
        }
    }
}
