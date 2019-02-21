from pprint import pprint
from datetime import datetime, date, timedelta

class DeliveryWindow:
    def __init__(self, starts_at=datetime.now(), ends_at=datetime.now()+timedelta(hours=2)):
        self.starts_at = starts_at
        self.ends_at = ends_at
        
    @classmethod
    def generate(cls):
        windows = []
        today = date.today()
        days = [0, 1, 2, 3, 4, 5, 6]
        hours = [8, 10, 12, 14, 16, 18, 20, 23]
        for d in days:
            day = today + timedelta(days=d)
            for h in hours:
                start = datetime(day.year, day.month, day.day, h)
                end = start+timedelta(hours=2)
                windows.append(DeliveryWindow(starts_at=start, ends_at=end))
        return windows
    
    def __str__(self):
        return "(Starts: {}, Ends: {})".format(self.starts_at, self.ends_at)
    
    def __repr__(self):
        return self.__str__()
        
class Address:
    def __init__(self, zip_code='10002', is_reception=False):
        self.zip_code = zip_code
        self.is_reception = is_reception
    
    @classmethod
    def available_zip_codes(self):
        return ["10001", "10002", "10003"]

class Medication:
    def __init__(self, drug='Lipitor', in_stock_date=date.today()):
        self.drug = drug
        self.in_stock_date = in_stock_date
    

class Order:
    def __init__(self, address=Address(), medications=[Medication()]):
        self.address = address
        self.medications = medications

    def available_windows(self):
        available_zips = Address.available_zip_codes()
        all_windows = DeliveryWindow.generate()

        # implement me
        # 1. if capsule delivers to the address, let's return the available windows
        # 2. if don't let's return empty array. 
        # 3. filter out available slots based on latest available medication
        # 4. validate latest approach by adding more medicines
        
        # implementation
        latest_available = date.today()
        print(latest_available)
        for medication in self.medications:
            if medication.in_stock_date > latest_available:
                latest_available = medication.in_stock_date
        
        now = datetime.now()+timedelta(minutes=45)
        for zip in available_zips:
            if self.address.zip_code == zip:
                all_windows_capped = []
                for dw in all_windows:
                    if dw.starts_at > now and dw.starts_at.date() >= latest_available:
                        all_windows_capped.append(dw)
                
                return all_windows_capped     
        
        return []        

medications = []
med1 = Medication()
future_date = datetime.now()+timedelta(days=2)
med2 = Medication('Omeprazol',future_date.date())
medications.append(med1)
medications.append(med2)
print(Order(medications=medications).available_windows())
