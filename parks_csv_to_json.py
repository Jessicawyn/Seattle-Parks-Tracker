import csv
import json
places_json = []
park = {}


with open('Seattle_Parks_And_Recreation_Park_Addresses.csv', 'r') as f:
    reader = csv.reader(f)
    next(reader)
    for row in reader:
        new_park = {
            "lat": row[6],
            "lng": row[5],
            "name": row[2],
            "rating": 5,
            "vicinity": row[3] + ", " + row[4]
            }
        parks_json.append(new_park)


with open('places.json', 'w') as f:
    json.dump(places_json, f, indent=4)
