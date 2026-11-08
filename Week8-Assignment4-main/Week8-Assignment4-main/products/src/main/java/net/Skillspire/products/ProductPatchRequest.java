package net.Skillspire.products;

public record ProductPatchRequest(
        String name,
        String category,
        Double price
) {
}
