package com.technical.test.satoritech.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.technical.test.satoritech.R
import com.technical.test.satoritech.model.User
import com.technical.test.satoritech.utils.getInitials
import com.technical.test.satoritech.utils.isLetters

@Composable
fun UserScreen(
    user: User,
    onClickProfile: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(50.dp),
        contentAlignment = Alignment.Center
    ) {
        if (user.urlImage.isNullOrEmpty()) {
            ShowInitials(user.name, onClickProfile)
        } else {
            ShowImage(user, onClickProfile)
        }
    }
}

@Composable
fun ShowPlaceholder(onClickProfile: () -> Unit) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        painter = painterResource(id = R.drawable.placeholder),
        contentScale = ContentScale.FillWidth,
        contentDescription = null,
    )
}

@Composable
fun ShowImage(user: User, onClickProfile: () -> Unit) {
    SubcomposeAsyncImage(
        modifier = Modifier.clip(CircleShape),
        model = user.urlImage,
        contentDescription = "Image User",
        contentScale = ContentScale.Crop,
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Error) {
            if (user.name.isNullOrEmpty()) {
                ShowPlaceholder(onClickProfile)
            } else {
                ShowInitials(name = user.name, onClickProfile)
            }
        } else {
            SubcomposeAsyncImageContent(
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        onClickProfile()
                    },
                contentScale = ContentScale.Inside
            )
        }
    }
}

@Composable
fun ShowInitials(name: String, onClickProfile: () -> Unit) {
    if (name.isLetters()) {
        Text(
            modifier = Modifier
                .drawBehind {
                    drawCircle(
                        color = Color.Green,
                        radius = size.maxDimension
                    )
                }
                .clickable {
                    onClickProfile()
                },
            text = name.getInitials(),
            style = TextStyle(color = Color.White, fontSize = 15.sp)
        )
    } else {
        ShowPlaceholder(onClickProfile)
    }
}


@Composable
@Preview(showBackground = true)
private fun UserScreenPreview() {
    UserScreen(user = User("Carlos Mauricio", ""), onClickProfile = {})
}
