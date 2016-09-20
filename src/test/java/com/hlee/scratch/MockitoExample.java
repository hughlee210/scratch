package com.hlee.scratch;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockitoExample {

	@Mock
	private List mockedList;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test1() {
		// mock creation

		// using mock object
		mockedList.add("one");
		mockedList.clear();
		when(mockedList.get(1)).thenReturn("first");
		System.out.println(mockedList.get(1));

		// verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
		verify(mockedList).get(1);

		// Once created, mock will remember all interactions. Then you can
		// selectively verify whatever interaction you are interested in.

		String test = null;
		test.toString();
	}

}
