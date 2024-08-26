package ecommersite.swiftshopper.exceptions;

public class CategoryNotFoundException extends RuntimeException
{
    public CategoryNotFoundException(String message)
    {
        super(message);
    }
}