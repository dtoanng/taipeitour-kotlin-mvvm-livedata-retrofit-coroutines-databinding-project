//package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.remote.paging
//
//import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.remote.AttractionsApiService
//import javax.inject.Inject
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
//import retrofit2.HttpException
//
//class AttractionsPagingDataSource @Inject constructor(private val apiService: AttractionsApiService, private val language: String) :
//    PagingSource<Int, Attraction>() {
//    override fun getRefreshKey(state: PagingState<Int, Attraction>): Int? = state.anchorPosition
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Attraction> {
//        return try {
//            val nextPage = params.key ?: 1
//            val attractions = apiService.getAllTouristAttractions(language, nextPage.toString())
//            LoadResult.Page(
//                data = attractions.touristAttraction,
//                prevKey = if (nextPage == 1) null else nextPage - 1,
//                nextKey = if (attractions.touristAttraction.isNotEmpty()) nextPage + 1 else null
//            )
//        } catch (exception: Exception) {
//            return LoadResult.Error(exception)
//        } catch (httpException: HttpException) {
//            return LoadResult.Error(httpException)
//        }
//    }
//}