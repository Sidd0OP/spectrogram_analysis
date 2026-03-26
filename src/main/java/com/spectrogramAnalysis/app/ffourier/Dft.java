package com.spectrogramAnalysis.app.ffourier;


public class Dft {

   int windowSize;
   FtCoefficient[][] dftMatrix;

   public Dft(int windowSize)
   {
      this.windowSize = windowSize;
      initDftMatrix();
   }

   private void initDftMatrix()
   {
      int N = windowSize;
      dftMatrix = new FtCoefficient[windowSize][windowSize];

      for (int k = 0; k < N; k++) {
         for (int n = 0; n < N; n++) {

            double angle = (2 * Math.PI * k * n) / N;
            dftMatrix[k][n] = new FtCoefficient(Math.cos(angle) , -Math.sin(angle));

         }
      }

   }

   public void dftMultiplication(int[] frqBucket , short[] data , int start)
   {
      int N = windowSize;

      // Ensure window does not exceed data length
      if (start + N > data.length) {
         N = data.length - start;
      }

      //this shit written by chat gpt brv
      //looks ok
      for (int k = 0; k < N; k++)
      {

         double realSum = 0;
         double imagSum = 0;

         for (int n = 0; n < N; n++)
         {

            int dataIndex = start + n;

            double sample = data[dataIndex];

            FtCoefficient coeff = dftMatrix[k][n];

            // Multiply: x[n] * W[k][n]
            realSum += sample * coeff.real;
            imagSum += sample * coeff.imaginary;
         }

         // Magnitude of complex number
         double magnitude = Math.sqrt(realSum * realSum + imagSum * imagSum);

         frqBucket[k] = (int) magnitude;
      }


   }

}
