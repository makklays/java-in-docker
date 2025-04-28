package com.techmatrix18.services;

import com.techmatrix18.repositories.BaseRepository;
import com.techmatrix18.model.Base;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BaseServiceTest {
    @Mock
    private BaseRepository baseRepository;

    @InjectMocks
    private BaseService baseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenBaseExists_thenReturnBook() {
        // given
        Base base = new Base();
        base.setId(1L);
        base.setTitle("Base #1");

        when(baseRepository.findByTitle("Base #1")).thenReturn(Optional.of(base));

        // when
        Base foundBase = baseService.findBaseByTitle("Base #1");

        // then
        assertNotNull(foundBase);
        assertEquals("Base #1", foundBase.getTitle());
    }

    @Test
    void whenBookDoesNotExist_thenThrowException() {
        // given
        when(baseRepository.findByTitle("Unknown Base")).thenReturn(Optional.empty());

        // when & then
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> baseService.findBaseByTitle("Unknown Book")
        );

        assertEquals("Base titled 'Unknown Base' not found", exception.getMessage());
    }
}

