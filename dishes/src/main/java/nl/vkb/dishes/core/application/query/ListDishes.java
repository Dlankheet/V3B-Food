package nl.vkb.dishes.core.application.query;

public record ListDishes(String orderBy, String direction) {
    public ListDishes {
        if (orderBy == null) {
            orderBy = "name";
        }

        if (direction == null) {
            direction = "asc";
        }

    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
