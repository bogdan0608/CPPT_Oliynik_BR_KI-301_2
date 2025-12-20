namespace lab3_19.api.Models;

public class Review
{
    public int Id { get; set; }
    public string Name { get; set; }
    public string Text { get; set; }
    public int Rating { get; set; }
    public int ServiceId { get; set; }
}