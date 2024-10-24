package tn.esprit.tpfoyer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlocServiceImplTest {

    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocServiceImpl blocService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    // Test retrieveAllBlocs method
    @Test
    void testRetrieveAllBlocs() {
        // Given
        List<Bloc> blocs = new ArrayList<>();
        Bloc bloc1 = new Bloc();
        bloc1.setIdBloc(1L);
        Bloc bloc2 = new Bloc();
        bloc2.setIdBloc(2L);
        blocs.add(bloc1);
        blocs.add(bloc2);

        // When
        when(blocRepository.findAll()).thenReturn(blocs);
        List<Bloc> result = blocService.retrieveAllBlocs();

        // Then
        assertEquals(2, result.size());  // Assert we got 2 blocs
        verify(blocRepository, times(1)).findAll(); // Verify interaction with repository
    }

    // Test retrieveBlocsSelonCapacite method
    @Test
    void testRetrieveBlocsSelonCapacite() {
        // Given
        List<Bloc> blocs = new ArrayList<>();
        Bloc bloc1 = new Bloc();
        bloc1.setCapaciteBloc(10);
        Bloc bloc2 = new Bloc();
        bloc2.setCapaciteBloc(20);
        blocs.add(bloc1);
        blocs.add(bloc2);

        // When
        when(blocRepository.findAll()).thenReturn(blocs);
        List<Bloc> result = blocService.retrieveBlocsSelonCapacite(15);

        // Then
        assertEquals(1, result.size());  // Only bloc2 has capacite >= 15
        assertEquals(20, result.get(0).getCapaciteBloc());
    }

    // Test retrieveBloc method
    @Test
    void testRetrieveBloc() {
        // Given
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);

        // When
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));
        Bloc result = blocService.retrieveBloc(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdBloc());
        verify(blocRepository, times(1)).findById(1L); // Verify method call
    }

    // Test addBloc method
    @Test
    void testAddBloc() {
        // Given
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");

        // When
        when(blocRepository.save(bloc)).thenReturn(bloc);
        Bloc result = blocService.addBloc(bloc);

        // Then
        assertNotNull(result);
        assertEquals("Bloc A", result.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    // Test modifyBloc method
    @Test
    void testModifyBloc() {
        // Given
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc Updated");

        // When
        when(blocRepository.save(bloc)).thenReturn(bloc);
        Bloc result = blocService.modifyBloc(bloc);

        // Then
        assertNotNull(result);
        assertEquals("Bloc Updated", result.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    // Test removeBloc method
    @Test
    void testRemoveBloc() {
        // Given
        Long blocId = 1L;

        // When
        doNothing().when(blocRepository).deleteById(blocId);
        blocService.removeBloc(blocId);

        // Then
        verify(blocRepository, times(1)).deleteById(blocId); // Ensure delete was called
    }

    // Test trouverBlocsSansFoyer method
    @Test
    void testTrouverBlocsSansFoyer() {
        // Given
        List<Bloc> blocs = new ArrayList<>();
        Bloc bloc1 = new Bloc();
        bloc1.setFoyer(null); // No Foyer
        Bloc bloc2 = new Bloc();
        bloc2.setFoyer(null); // No Foyer
        blocs.add(bloc1);
        blocs.add(bloc2);

        // When
        when(blocRepository.findAllByFoyerIsNull()).thenReturn(blocs);
        List<Bloc> result = blocService.trouverBlocsSansFoyer();

        // Then
        assertEquals(2, result.size());  // Both blocs have no Foyer
        verify(blocRepository, times(1)).findAllByFoyerIsNull();
    }

    // Test trouverBlocsParNomEtCap method
    @Test
    void testTrouverBlocsParNomEtCap() {
        // Given
        List<Bloc> blocs = new ArrayList<>();
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");
        bloc.setCapaciteBloc(50);
        blocs.add(bloc);

        // When
        when(blocRepository.findAllByNomBlocAndCapaciteBloc("Bloc A", 50)).thenReturn(blocs);
        List<Bloc> result = blocService.trouverBlocsParNomEtCap("Bloc A", 50);

        // Then
        assertEquals(1, result.size());
        assertEquals("Bloc A", result.get(0).getNomBloc());
        verify(blocRepository, times(1)).findAllByNomBlocAndCapaciteBloc("Bloc A", 50);
    }
}
