package com.cankarademir.atmosware_internship_project.ui.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import coil.compose.rememberImagePainter
import com.cankarademir.atmosware_internship_project.R
import com.cankarademir.atmosware_internship_project.databinding.FragmentFavoriteBinding
import com.cankarademir.atmosware_internship_project.models.Photos

class FavoriteFragment : Fragment() {
    private val navController by lazy {
        Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment_activity_main
        )
    }
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        favoriteViewModel.getPhotosData()

        binding.composeView.setContent {
            FavoriteFragmentt(navController, viewModel = favoriteViewModel)
        }
    }
}

@Composable
fun FavoriteFragmentt(navController: NavController, viewModel: FavoriteViewModel) {

    val dataList: List<Photos>? by viewModel.data.collectAsState(initial = emptyList())
    LaunchedEffect(viewModel) {
        viewModel.getPhotosData()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TitleText(R.string.FavoriteTitle)
        dataList?.let { photosList ->
            CustomRecyclerView(dataList = photosList) { item ->
                val action =
                    FavoriteFragmentDirections.actionNavigationFavoritesToDetailPhotoFragment(item)
                navController.navigate(action)
            }
        }
    }
}

@Composable
fun CustomRecyclerView(dataList: List<Photos>, onItemClick: (Photos) -> Unit) {
    Column() {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(dataList) { item ->
                ComposeItemPhoto(photos = item, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
fun ComposeItemPhoto(photos: Photos, onItemClick: (Photos) -> Unit) {
    Surface(
        color = Color.White,
        modifier = Modifier.padding(3.dp) then Modifier.clickable { onItemClick(photos) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RemoteImage(photos.url, " ")
            Spacer(modifier = Modifier.height(16.dp))

            photos.title?.let {
                Text(
                    text = it,
                    color = Color.Black,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}

@Composable
fun RemoteImage(url: String, contentDescription: String) {
    val painter = rememberImagePainter(url)

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .height(190.dp)
            .width(190.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TitleText(title: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = title),
            style = TextStyle(
                fontSize = 21.sp,
                color = Color(0xFF6750A4),
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

