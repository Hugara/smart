# smart
Image Steganography
Steganography is the art of hiding secret information inside another file (called a cover object) such that the existence of the hidden data is not noticeable.
In image steganography, we hide text data inside an image without visibly changing the image.
👉 Difference from cryptography:
Cryptography hides the content
Steganography hides the existence
2️⃣ Why Image Steganography?
✔ Images contain large amounts of redundant data
✔ Minor pixel changes are invisible to the human eye
✔ Easy to transmit over the internet
✔ Good for secure communication & watermarking
3️⃣ Technique Used: LSB (Least Significant Bit)
🔹 Why LSB?
Each pixel in an RGB image has 3 color values:
Red (8 bits)
Green (8 bits)
Blue (8 bits)
Changing the least significant bit (LSB) of these values does not visibly affect the image.
🔹 Example:
Original Red value: 10010110
After hiding bit 1 : 10010111
Change is imperceptible 
4️⃣ Working Principle
🔐 Encoding (Hiding Text)
Read the image
Convert secret message into binary
Modify the LSB of each pixel to store message bits
Save the stego-image
🔓 Decoding (Extracting Text)
Read the stego-image
Extract LSB bits from pixels
Convert binary back to text
5️⃣ Project Architecture
Copy code

ImageSteganography/
│
├── Steganography.java
├── input.png          (cover image)
├── stego.png          (output image)
└── README.txt
