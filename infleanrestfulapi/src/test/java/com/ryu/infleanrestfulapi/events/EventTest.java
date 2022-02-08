package com.ryu.infleanrestfulapi.events;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class EventTest {

    @Test
    public void builder(){
        Event event = Event.builder().build();

        Assertions.assertThat(event).isNotNull();
    }

    @Test
    public void javaBean(){
        String name = "Event";
        String description = "Spring";

        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        Assertions.assertThat(event.getName()).isEqualTo(name);
        Assertions.assertThat(event.getDescription()).isEqualTo(description);
    }

    @ParameterizedTest
    @MethodSource("paramsForTestFree")
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        // given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        // when
        event.update();

        // then
        Assertions.assertThat(event.isFree()).isEqualTo(isFree);
    }

    @ParameterizedTest
    @MethodSource("paramsForTestOffline")
    void testOffline(String location, boolean isOffline) {
        // given
        Event event = Event.builder()
                .location(location)
                .build();

        // when
        event.update();

        // then
        Assertions.assertThat(event.isOffline()).isEqualTo(isOffline);
    }

    private static Stream<Arguments> paramsForTestFree() { // argument source method
        return Stream.of(
                Arguments.of(0,0, true),
                Arguments.of(100, 0, false),
                Arguments.of(0, 100, false),
                Arguments.of(100, 200, false)
        );
    }

    private static Stream<Arguments> paramsForTestOffline() { // argument source method
        return Stream.of(
                Arguments.of("강남", true),
                Arguments.of(null, false),
                Arguments.of("        ", false)
        );
    }


    @Test
    public void testFree(){

        //given
        Event event = Event.builder()
                .basePrice(0)
                .maxPrice(0)
                .build();
        //when
        event.update();
        //then
        Assertions.assertThat(event.isFree()).isTrue();

        //given
        event = Event.builder()
                .basePrice(100)
                .maxPrice(0)
                .build();

        //when
        event.update();
        //then
        Assertions.assertThat(event.isFree()).isFalse();

        //given
        event = Event.builder()
                .basePrice(0)
                .maxPrice(100)
                .build();

        //when
        event.update();
        //then
        Assertions.assertThat(event.isFree()).isFalse();
    }

    @Test
    public void testOffline(){

        //given
        Event event = Event.builder()
                .location("강남역 네이버")
                .build();
        //when
        event.update();
        //then
        Assertions.assertThat(event.isOffline()).isTrue();

        //given
        event = Event.builder()
                .build();
        //when
        event.update();
        //then
        Assertions.assertThat(event.isOffline()).isFalse();


    }
}