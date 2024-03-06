import os
import time
def deleteFile():
    directory = 'removed_background/'
    now_time = time.time()
    for filename in os.listdir(directory):
        filepath = os.path.join(directory, filename)
        if os.path.isfile(filepath):
            file_creat_time = os.path.getctime(filepath)
            if (now_time - file_creat_time) > 3600:
                os.remove(filepath)



