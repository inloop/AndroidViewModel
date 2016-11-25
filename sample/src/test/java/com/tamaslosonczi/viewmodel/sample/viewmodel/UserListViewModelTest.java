package com.tamaslosonczi.viewmodel.sample.viewmodel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tamaslosonczi.viewmodel.sample.loader.UserLoader;
import com.tamaslosonczi.viewmodel.sample.viewmodel.view.IUserListView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserListViewModelTest {

	private UserListViewModel sut;

	@Mock
	private UserLoader loader;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		sut = new UserListViewModel(loader);
	}

	@Test
	public void testOnBindViewWhenViewNotNullAndLoaderIsLoading() {
		//given
		IUserListView view = mock(IUserListView.class);
		//when
		sut.onBindView(view);
		when(loader.isLoading()).thenReturn(true);
		//then
		verify(loader, times(1)).getProgress();
	}

	@Test
	public void testOnBindViewWhenViewNotNullAndLoaderIsNotLoading() {
		//given
		IUserListView view = mock(IUserListView.class);
		//when
		sut.onBindView(view);
		when(loader.isLoading()).thenReturn(false);
		//then
		verify(loader, times(1)).loadUser();
	}

}
