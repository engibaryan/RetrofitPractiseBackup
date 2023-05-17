package com.example.retrofitlesson



import androidx.appcompat.widget.SearchView
import com.example.retrofitlesson.adapter.ProductAdapter
import com.example.retrofitlesson.retrofit.ProductApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

internal class MainActivityTest {
    @Mock
    private lateinit var mockProductApi: ProductApi

    @Mock
    private lateinit var mockAdapter: ProductAdapter

    @Mock
    private lateinit var mockSearchView: SearchView

    private lateinit var mainActivity: MainActivity

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainActivity = MainActivity()
        val productApi = mockProductApi
        mainActivity.adapter = mockAdapter
        mainActivity.searchView = mockSearchView
    }

    @Test
    fun testButtonClick() {
        val category = "Category 1"
        val mockedButton = mock(Button::class.java)
        `when`(mockedButton.text).thenReturn(category)

        mainActivity.onClick(mockedButton)

        verify(mockProductApi).getCategory(category)
        verify(mockAdapter).submitList(anyList())
    }

    @Test
    fun testSearchQuery() {
        val query = "Search query"
        val mockedQueryTextListener = mock(SearchView.OnQueryTextListener::class.java)
        `when`(mockSearchView.query).thenReturn(query)
        `when`(mockSearchView.setOnQueryTextListener(any(SearchView.OnQueryTextListener::class.java))).then {
            val listener = it.getArgument<SearchView.OnQueryTextListener>(0)
            listener.onQueryTextSubmit(query)
            true
        }

        mainActivity.onCreate(null) // Simulate onCreate to set the listener

        // Verify that the query is passed to the API and adapter
        verify(mockProductApi).getProductsByName(query)
        verify(mockAdapter).submitList(anyList())
    }

    @Test
    fun testArticleClick() {
        val mockedArticle = mock(ArticleX::class.java)
        val mockedIntent = mock(Intent::class.java)
        val expectedTitle = "Article Title"
        val expectedImage = "http://example.com/image.jpg"

        `when`(mockedArticle.title).thenReturn(expectedTitle)
        `when`(mockedArticle.urlToImage).thenReturn(expectedImage)
        `when`(mockedIntent.putExtra(anyString(), anyString())).thenReturn(mockedIntent)
        `when`(mockedIntent.putExtra(anyString(), anyLong())).thenReturn(mockedIntent)
        `when`(mockedIntent.putExtra(anyString(), anyString())).thenReturn(mockedIntent)
        `when`(mockedIntent.putExtra(anyString(), anyString())).thenReturn(mockedIntent)
        `when`(mockedIntent.putExtra(anyString(), anyString())).thenReturn(mockedIntent)
        `when`(mockedIntent.putExtra(anyString(), anyString())).thenReturn(mockedIntent)
        `when`(mockedIntent.putExtra(anyString(), anyString())).thenReturn(mockedIntent)

        mainActivity.onClickArticles(mockedArticle)

        // Verify that the intent is properly configured and started
        verify(mockedIntent).putExtra("title", expectedTitle)
        verify(mockedIntent).putExtra("img", expectedImage)
        verify(mainActivity).startActivity(mockedIntent)

    }
}