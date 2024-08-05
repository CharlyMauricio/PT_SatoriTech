package com.technical.test.satoritech.ui.user

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
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
import com.technical.test.satoritech.utils.alphabetCheck
import com.technical.test.satoritech.utils.getInitials
import com.technical.test.satoritech.utils.isLetters

@Composable
fun UserScreen(
    user: User
) {
    Box(
        modifier = Modifier
            .size(150.dp),
        contentAlignment = Alignment.Center
    ) {
        if(user.urlImage.isNullOrEmpty()){
            ShowInitials(user.name)
        } else {
            ShowImage(user)
        }
    }
}

@Composable
fun ShowPlaceholder() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        painter = painterResource(id = R.drawable.placeholder),
        contentScale = ContentScale.FillWidth,
        contentDescription = null,
    )}

@Composable
fun ShowImage(user: User) {
    SubcomposeAsyncImage(
        modifier = Modifier.clip(CircleShape),
        model = user.urlImage,
        contentDescription = "Image User",
        contentScale = ContentScale.Crop,
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Error) {
            if (user.name.isNullOrEmpty()){
                ShowPlaceholder()
            } else {
                ShowInitials(name = user.name)
            }
        } else {
            SubcomposeAsyncImageContent(
                contentScale = ContentScale.Inside
            )
        }
    }
}

@Composable
fun ShowInitials(name: String) {
    if (name.isLetters()) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .drawBehind {
                    drawCircle(
                        color = Color.Green,
                        radius = size.maxDimension
                    )
                },
            text = name.getInitials(),
            style = TextStyle(color = Color.White, fontSize = 20.sp)
        )
    } else {
        ShowPlaceholder()
    }
}


@Composable
@Preview(showBackground = true)
private fun UserScreenPreview() {
    UserScreen(user = User("Carlos Mauricio", ""))
}
