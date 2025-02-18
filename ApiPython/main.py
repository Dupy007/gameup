from fastapi import FastAPI, HTTPException
from recommendation import generate_recommendations
from models import UserData
from fastapi.encoders import jsonable_encoder
import numpy as np
import json

class NumpyEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.integer):
            return int(obj)
        elif isinstance(obj, np.floating):
            return float(obj)
        elif isinstance(obj, np.ndarray):
            return obj.tolist()
        return super().default(obj)
app = FastAPI()

# Endpoint de base pour tester que l'API est en ligne
@app.get("/")
async def root():
    return {"message": "API de recommandation en ligne"}

# Endpoint pour envoyer les données d'utilisateur et récupérer des recommandations
@app.post("/recommendations/")
async def get_recommendations(data: UserData):
    try:
        recommendations = generate_recommendations(data)
        # Utiliser l'encodeur personnalisé pour convertir la réponse en JSON
        return json.loads(json.dumps({"recommendations": recommendations}, cls=NumpyEncoder))
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
