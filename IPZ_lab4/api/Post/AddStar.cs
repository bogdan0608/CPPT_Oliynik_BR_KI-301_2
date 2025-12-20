using System.Windows;
using lab4_19.Entity;

namespace lab4_19.api.Post;

public class AddStar
{
    public AddStar(Service service, int selectedRating)
    {
        MessageBox.Show($"You rated {selectedRating} star(s)!", "Rating Sent", MessageBoxButton.OK, MessageBoxImage.Information);
    }
}