from django.shortcuts import render
from django.http import HttpResponseRedirect, JsonResponse
from django.core.files.storage import FileSystemStorage
from django.views.decorators.csrf import csrf_exempt
import base64
from .utils.cnn import predict_out

# Create your views here.
@csrf_exempt
def get_score(request):
	if request.method == 'POST':
		logo = request.POST['logo']
		logo = bytes(logo, encoding='UTF-8')
		with open("/home/vasu/Desktop/Beagos/media/imageToSave.jpeg", "wb") as fh:
			fh.write(base64.decodestring(logo))
		score = predict_out("/home/vasu/Desktop/Beagos/media/imageToSave.jpeg")
		return JsonResponse({'score':score})
	else:
		return render(request, "main/thing.html")
		return JsonResponse({'Error':'No POST request'})
