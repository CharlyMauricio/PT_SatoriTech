package com.technical.test.satoritech.ui.utils.composable

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.technical.test.satoritech.R

@Composable
fun ErrorDialog(
    messageId: Int,
    onDialogDismiss: () -> Unit,
) {
    AlertDialog(
        modifier = Modifier
            .semantics { testTag = "error-dialog" },
        onDismissRequest = { },
        title = {
            Text(stringResource(R.string.error_title))
        },
        text = {
            Text(stringResource(id = messageId))
        },
        confirmButton = {
            Button(onClick = { onDialogDismiss() }) {
                Text(stringResource(R.string.try_again))
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun ErrorDialogPreview() {
    ErrorDialog(
        R.string.error_generico,
        onDialogDismiss = {}
    )
}