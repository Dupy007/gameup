import json
import pickle
from models import UserData

# Chargement du modèle pré-entraîné (assurez-vous que le fichier existe)
MODEL_FILENAME = "knn_model.pkl"
try:
    print("Loading model...")
    with open(MODEL_FILENAME, "rb") as f:
        knn_model = pickle.load(f)
except Exception as e:
    knn_model = None
    print("Modèle  non chargé:", e)

def transform_user_data(user_data):
    purchases = user_data.purchases
    num_purchases = len(purchases)

    # S'il n'y a pas d'achat, retourner un vecteur neutre
    if num_purchases == 0:
        return [0, 0, 0, 0,0,0]

    total_rating = sum(p.rating for p in purchases)
    average_rating = total_rating / num_purchases
    max_rating = max(p.rating for p in purchases)
    min_rating = min(p.rating for p in purchases)
    min_gameId = min(p.game_id for p in purchases)
    return [min_rating,min_gameId,user_data.user_id,num_purchases,max_rating,average_rating]
    # return [num_purchases, average_rating, max_rating, min_rating]

def generate_recommendations(user_data: UserData):
    if knn_model is None:
        return []
    user_features = transform_user_data(user_data)
    distances, indices = knn_model.kneighbors([user_features])
    recommendations = []
    print("Distances:", distances)
    print("Indices:", indices)
    for idx in indices[0]:
        recommendations.append({"game_id": idx})
    return recommendations
