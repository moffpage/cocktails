package kz.grandera.vlifetesttaskapp.ui.details

import android.annotation.SuppressLint

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.annotation.DrawableRes

import coil.compose.AsyncImage

import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

import kz.grandera.vlifetesttaskapp.R
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent
import kz.grandera.vlifetesttaskapp.features.details.component.CocktailDetailsComponent.DrinkCategory
import kz.grandera.vlifetesttaskapp.resources.Strings
import kz.grandera.vlifetesttaskapp.ui_components.R as UiCompR
import kz.grandera.vlifetesttaskapp.ui_components.error.ErrorContent
import kz.grandera.vlifetesttaskapp.ui_components.modifier.verticalFadingEdge

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
internal fun CocktailDetailsContent(
    modifier: Modifier = Modifier,
    component: CocktailDetailsComponent
) {
    val model by component.model.subscribeAsState()

    Box(modifier = modifier.fillMaxSize()) {
        if (model.isError) {
            ErrorContent(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(horizontal = 64.dp),
                onRetry = { component.refetchDetails() }
            )
        } else {
            Surface(color = MaterialTheme.colors.background) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box {
                        var imageHeight by remember { mutableFloatStateOf(value = 0f) }
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(ratio = 1f)
                                .onSizeChanged { size ->
                                    imageHeight = size.height.toFloat()
                                }
                                .verticalFadingEdge(
                                    startY = imageHeight * (2/3),
                                    endY = imageHeight,
                                    endColor = MaterialTheme.colors.background,
                                    startColor = Color.Transparent,
                                ),
                            model = model.imageUrl,
                            contentDescription = model.cocktailName
                        )
                        Text(
                            modifier = Modifier
                                .align(alignment = Alignment.BottomCenter)
                                .padding(bottom = 16.dp)
                                .padding(horizontal = 16.dp),
                            text = model.cocktailName,
                            style = MaterialTheme.typography.h1
                                .copy(
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                    ) {
                        Chip(
                            text = model.glassType,
                            iconResourceId = model.category?.iconResourceId
                        )
                        Chip(
                            text = if (model.isAlcoholic) {
                                stringResource(id = Strings.alcoholic.resourceId)
                            } else {
                                stringResource(id = Strings.nonAlcoholic.resourceId)
                            },
                            iconResourceId = null
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .padding(horizontal = 8.dp),
                        text = stringResource(id = Strings.instructions.resourceId),
                        style = MaterialTheme.typography.h2
                            .copy(color = MaterialTheme.colors.primary)
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 8.dp),
                        text = model.preparationInstruction,
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        }
        IconButton(
            modifier = Modifier
                .padding(start = 12.dp)
                .statusBarsPadding()
                .displayCutoutPadding()
                .clip(shape = CircleShape)
                .background(
                    color = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                    shape = CircleShape
                ),
            onClick = { component.navigateBack() }
        ) {
            Image(
                painter = painterResource(id = UiCompR.drawable.back),
                contentDescription = null
            )
        }
    }
}

private val DrinkCategory.iconResourceId: Int get() =
    when (this) {
        DrinkCategory.Beer -> R.drawable.beer
        DrinkCategory.Shot -> R.drawable.shot
        DrinkCategory.Shake -> R.drawable.shake
        DrinkCategory.Soft -> R.drawable.soft_drink
        DrinkCategory.Cocoa -> R.drawable.cocoa
        DrinkCategory.Coffee -> R.drawable.coffee
        DrinkCategory.Other -> R.drawable.other
        DrinkCategory.Punch -> R.drawable.punch
        DrinkCategory.Liqueur -> R.drawable.liqueur
        DrinkCategory.Cocktail -> R.drawable.cocktail
        DrinkCategory.Ordinary -> R.drawable.regular_drink
    }

@Composable
private fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes iconResourceId: Int?,
) {
    val alcoholicIndicationColor = if (iconResourceId != null) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.secondary
    }

    Surface(
        modifier = modifier,
        shape = CircleShape,
        contentColor = alcoholicIndicationColor
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(height = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        ) {
            if (iconResourceId != null) {
                Icon(
                    painter = painterResource(id = iconResourceId),
                    contentDescription = text
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.h4
                    .copy(
                        color = alcoholicIndicationColor,
                        textAlign = TextAlign.Center
                    )
            )
        }
    }
}