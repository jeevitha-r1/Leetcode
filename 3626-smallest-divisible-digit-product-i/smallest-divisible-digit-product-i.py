class Solution:
    def smallestNumber(self, n: int, t: int) -> int:
        curr = n
        while True:
            # Calculate the product of digits for the current number
            prod = 1
            temp = curr
            while temp > 0:
                prod *= temp % 10
                temp //= 10
            
            # Check if product is divisible by t
            if prod % t == 0:
                return curr
            
            curr += 1