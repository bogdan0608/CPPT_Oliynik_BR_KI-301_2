using System.Collections.ObjectModel;
using System.Threading.Tasks;
using lab2_19.Entity;

namespace lab2_19.api.Get
{
    public class GetServices
    {
        // Емуляція відповіді від сервера
        public static async Task<ObservableCollection<Service>> GetServicesResponseAsync()
        {
            await Task.Delay(300); // Імітація затримки запиту до сервера

            // Статичний список послуг з ремонту автомобілів
            var mockServices = new ObservableCollection<Service>
            {
                new Service("Діагностика двигуна", 800),
                new Service("Заміна масла та фільтрів", 1200),
                new Service("Регулювання гальмівної системи", 950),
                new Service("Ремонт ходової частини", 2200),
                new Service("Заміна свічок запалювання", 600),
                new Service("Розвал-сходження", 1000),
                new Service("Полірування кузова", 1500),
                new Service("Заміна шин", 700)
            };

            return mockServices;
        }

        // Клас-заглушка для сумісності з початковим кодом
        private class ResponseWrapper
        {
            public List<Service> message { get; set; }
        }
    }
}