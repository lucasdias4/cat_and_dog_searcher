package com.lucasdias.feature_animal.list

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.enum.AnimalType
import com.lucasdias.domain.enum.RequestType
import com.lucasdias.domain.model.Animal
import com.lucasdias.domain.usecase.SearchAnimalByNameAndType
import com.lucasdias.feature_animal.util.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AnimalListViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private val searchAnimalByNameAndType: SearchAnimalByNameAndType = mockk()
    private val viewModel = spyk(
        AnimalListViewModel(
            searchAnimalByNameAndType,
            coroutinesTestRule.testDispatcher
        )
    )

    @Test
    fun `IF viewModel execute request method THEN it calls searchAnimalByNameAndType use case by passing the correct parameters`() =
        runBlockingTest {
            coEvery { searchAnimalByNameAndType(any(), any()) } returns successResource
            val searchText = viewModel.searchText
            val requestType = viewModel.requestType

            viewModel.request()

            coVerify(exactly = 1) { searchAnimalByNameAndType(searchText, requestType) }
        }

    @Test
    fun `IF app try to setup request information THEN it will update searchText and requestType from viewModel`() {
        val expectedSearchText = "abcd"
        val expectedRequestType = RequestType.DOG

        viewModel.setupRequest(expectedSearchText, expectedRequestType)

        val actualSearchText = viewModel.searchText
        val actualRequestType = viewModel.requestType

        assertEquals(expectedSearchText, actualSearchText)
        assertEquals(expectedRequestType, actualRequestType)
    }

    private companion object {
        private val successResource = Resource.Success<List<Animal>>(
            mutableListOf(
                Animal("_", "A", "B", "C", "D", "E", AnimalType.CAT)
            )
        )
    }
}
