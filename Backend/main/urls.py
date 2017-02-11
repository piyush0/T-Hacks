from django.conf.urls import url
from .views import get_score

urlpatterns = [
    url(r'^get_score', get_score),
]