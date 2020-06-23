package cs3310hw2;

import java.util.*;

public class Sort 
{
    public Sort() {}
    
// INSERTION SORT
    public void insertionSort(int array[], int low, int n)
    {
        for(int i = low + 1; i < n; i++)
        {
            int value = array[i], j = i - 1;
            
            while(j >= low && array[j] > value)
            {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = value;
        }
    }

// MERGE SORT
    public void mergeSort(int array[], int left, int right)
    {
        if(left < right)
        {
            int middle = (left + right) / 2;
            
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            
            merge(array, left, middle, right);
        }
    }
    
    public void merge(int array[], int left, int middle, int right)
    {
        // sublist sizes
        int n1 = middle - left + 1;
        int n2 = right - middle;
        
        // temp arrays
        int leftArray[] = new int[n1];
        int rightArray[] = new int[n2];
        
        for(int i = 0; i < leftArray.length; i++)
        {
            leftArray[i] = array[left + i];
        }
        for(int i = 0; i < rightArray.length; i++)
        {
            rightArray[i] = array[middle + 1 + i];
        }
        
        // merge temp arrays
        // i = index of sublist 1, j = index of sublist 2, k = index of merged
        int i = 0, j = 0, k = left;
        
        while(i < n1 && j < n2)
        {
            if(leftArray[i] <= rightArray[j])
            {
                array[k] = leftArray[i];
                i++;
            }
            else
            {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while(i < n1)
        {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        
        while(j < n2)
        {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
// QUICK SORT 1
    public void quickSort1(int array[], int low, int high)
    {
        if(low < high)
        {
            int pivot = partition(array, low, high);
            
            quickSort1(array, low, pivot - 1);
            quickSort1(array, pivot + 1, high);
        }
    }
    
// QUICK SORT 2
    public void quickSort2(int array[], int low, int high)
    {
        while(low < high)
        {
            if(high - low < 16)
            {
                insertionSort(array, low, high + 1);
                break;
            }
            else
            {
                int pivot = partition(array, low, high);
            
                if(pivot - low < high - pivot)
                {
                    quickSort2(array, low, pivot - 1);
                    low = pivot + 1;
                }
                else
                {
                    quickSort2(array, pivot + 1, high);
                    high = pivot - 1;
                }
            }
        }
    }
    
// QUICK SORT 3
    public void quickSort3(int array[], int low, int high)
    {
        while(low < high)
        {
            Random random = new Random();
            int randomIndex = random.nextInt(array.length);

            if(array.length > 16)
            {
                int temp = array[low];
                array[low] = array[randomIndex];
                array[randomIndex] = temp;
            }

            int pivot = partition(array, low, high);

            if(pivot - low < high - pivot)
            {
                quickSort2(array, low, pivot - 1);
                low = pivot + 1;
            }
            else
            {
                quickSort2(array, pivot + 1, high);
                high = pivot - 1;
            }
        }
    }
    
    public int partition(int array[], int low, int high)
    {
        int i = low - 1, pivot = array[high];
        
        for (int j = low; j < high; j++) 
        { 
            if (array[j] < pivot) 
            { 
                i++;
                
                // exchange array[i] and array[j] 
                int temp = array[i]; 
                array[i] = array[j]; 
                array[j] = temp; 
            } 
        } 
  
        // exchange array[i + 1] and array[high] (pivot) 
        int temp = array[i + 1]; 
        array[i + 1] = array[high]; 
        array[high] = temp; 
  
        return i + 1; 
    }
}
