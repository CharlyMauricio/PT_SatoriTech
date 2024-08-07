package com.technical.test.satoritech.ui.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.technical.test.satoritech.R
import com.technical.test.satoritech.model.User
import com.technical.test.satoritech.ui.profile.UserScreen

@Composable
fun TopBarNavigation(
    user: User,
    onClickBack: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackNavigationIcon(onClickBack)

        Text(stringResource(R.string.name_app))

        UserScreen(
            user = user,
            onClickProfile = { onClick() }
        )
    }
}