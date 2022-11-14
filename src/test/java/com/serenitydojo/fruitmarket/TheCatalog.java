package com.serenitydojo.fruitmarket;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.serenitydojo.fruitmarket.FruitName.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class TheCatalog {

    private Catalog catalog;

    @Before
    public void setup() {
        this.catalog = new Catalog();
    }

    @Test
    public void shouldBeAbleToUpdateTheCurrentPriceOfAFruit() throws FruitUnavailableException {
        this.catalog.setPriceOf(Apple).to(4.00);
        assertThat(this.catalog.getPriceOf(Apple)).isEqualTo(4.00);
    }

    @Test
    public void shouldBeAbleToUpdateThePriceOfAExistingFruit() throws FruitUnavailableException {
        // Given
        this.catalog.setPriceOf(Apple).to(4.00);

        // When
        double newPrice = 8.0;
        this.catalog.setPriceOf(Apple).to(newPrice);

        // Then
        assertThat(this.catalog.getPriceOf(Apple)).isEqualTo(newPrice);
    }

    @Test
    public void shouldListTheFruitNamesInAlphabeticalOrder() {
        // Given
        this.catalog.setPriceOf(Apple).to(4.00);
        this.catalog.setPriceOf(Orange).to(5.5);
        this.catalog.setPriceOf(Banana).to(6.0);
        this.catalog.setPriceOf(Pear).to(4.5);

        // When
        List<String> availableFruitsInAlphabeticalOrder = this.catalog.getFruitNames();

        // Then
        assertThat(availableFruitsInAlphabeticalOrder).containsExactly("Apple", "Banana", "Orange", "Pear");
    }

    @Test
    public void shouldReportThePriceOfAGivenTypeOfFruit() throws FruitUnavailableException {
        // Given
        double priceOfApple = 4.0;
        this.catalog.setPriceOf(Apple).to(priceOfApple);

        double priceOfOrange = 5.5;
        this.catalog.setPriceOf(Orange).to(priceOfOrange);

        // When
        double reportedPriceOfApple = this.catalog.getPriceOf(Apple);
        double reportedPriceOfOrange = this.catalog.getPriceOf(Orange);

        // Then
        assertThat(reportedPriceOfApple).isEqualTo(priceOfApple);
        assertThat(reportedPriceOfOrange).isEqualTo(priceOfOrange);
    }

    @Test
    public void shouldThrowAExceptionIfFruitIsNotAvailable() {
        // Given
        this.catalog.setPriceOf(Apple).to(4.0);

        // Then
        assertThatThrownBy(() -> this.catalog.getPriceOf(Orange)).isInstanceOf(FruitUnavailableException.class);
    }

    @Test
    public void shouldThrowAExceptionIfPriceIsNegative() {
        // Then
        assertThatThrownBy(() -> this.catalog.setPriceOf(Apple).to(-4.0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
