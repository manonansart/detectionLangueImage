chmod 777 /var/www/uploads/userfile
cp /var/www/uploads/userfile /home/paoandroid/detectionLangueImage/images/
cd /home/paoandroid/detectionLangueImage/
ocropus-nlbin images/userfile -o traitement
ocropus-gpageseg -n 'traitement/0001.bin.png'
ocropus-rpred traitement/0001/*.bin.png
ocropus-hocr traitement/0001.bin.png

rm -Rf traitement
html2text -o userfile.txt book.html
