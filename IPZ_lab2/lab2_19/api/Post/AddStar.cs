using System.Windows;
using lab2_19.Entity;

namespace lab2_19.api.Post;

public class AddStar
{
    public AddStar(Service service, int selectedRating)
    {
        MessageBox.Show($"You rated {selectedRating} star(s)!", "Rating Sent", MessageBoxButton.OK, MessageBoxImage.Information);
    }
}