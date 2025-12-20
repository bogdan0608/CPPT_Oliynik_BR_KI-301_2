using System.Collections.ObjectModel;
using System.Threading.Tasks;
using lab2_19.Entity;

namespace lab2_19.api.Get
{
    public class GetReviews
    {
        // Емуляція відповіді від сервера
        public static async Task<ObservableCollection<Review>> GetReviewsResponseAsync(int ServiceId)
        {
            await Task.Delay(300); // Імітація затримки запиту

            // Статичні відгуки, які "належать" до різних ServiceId
            var allReviews = new List<Review>
                    {
                        new Review("Дуже сподобався сервіс! Рекомендую."),
                        new Review("Все пройшло швидко та якісно."),
                        new Review("Персонал привітний, ціни адекватні.")
                    };
            // Інакше повертаємо список відгуків для відповідного сервісу
            return new ObservableCollection<Review>(allReviews);
        }

        // Клас-заглушка для сумісності (залишено, щоб не ламати існуючий код)
        private class ResponseWrapper
        {
            public List<Review> message { get; set; }
        }
    }
}
