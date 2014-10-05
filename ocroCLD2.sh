echo -n "Quelle image voulez-vous traiter ? "
read image

ocropus-nlbin -o traitement images/$image
ocropus-gpageseg traitement/0001.bin.png
ocropus-rpred traitement/0001/*.bin.png
ocropus-hocr traitement/0001.bin.png

rm -Rf traitement
mv book.html resultats/$image.html
cd resultats
html2text -o $image.txt $image.html
rm $image.html

clear
cat $image.txt | cld2
