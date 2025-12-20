using System.Threading.Tasks;
using lab2_19.Entity;

namespace lab2_19.api.Post
{
    public class AddReview
    {
        // Емуляція відповіді від сервера
        public static async Task<bool> AddReviewRequestAsync(string Name, string Text, int Rating, int ServiceId)
        {
            await Task.Delay(300); // Імітація затримки запиту

            // Лог для тестування (імітація збереження на сервері)
            Console.WriteLine("[MockServer] Новий відгук створено:");
            Console.WriteLine($"Ім'я: {Name}");
            Console.WriteLine($"Оцінка: {Rating}/5");
            Console.WriteLine($"Відгук: {Text}");
            Console.WriteLine($"ServiceId: {ServiceId}");

            // Завжди успішна відповідь
            return true;
        }

        // Залишено для сумісності
        public class ResponseWrapper
        {
            public bool success { get; set; }
            public string message { get; set; }
        }
    }
}