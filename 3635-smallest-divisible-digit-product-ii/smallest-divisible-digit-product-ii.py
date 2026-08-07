class Solution:
    def smallestNumber(self, num: str, t: int) -> str:

        # Factor t into powers of 2, 3, 5, 7
        target = [0, 0, 0, 0]
        primes = [2, 3, 5, 7]

        for i, p in enumerate(primes):
            while t % p == 0:
                target[i] += 1
                t //= p

        # Digits 1..9 cannot provide any other prime factor
        if t != 1:
            return "-1"

        # Prime-factor contribution of every digit
        factors = [
            (0, 0, 0, 0),  # 0
            (0, 0, 0, 0),  # 1
            (1, 0, 0, 0),  # 2
            (0, 1, 0, 0),  # 3
            (2, 0, 0, 0),  # 4
            (0, 0, 1, 0),  # 5
            (1, 1, 0, 0),  # 6
            (0, 0, 0, 1),  # 7
            (3, 0, 0, 0),  # 8
            (0, 2, 0, 0),  # 9
        ]

        n = len(num)

        # ---------------------------------------------------------
        # DP for minimum number of digits needed for powers of 2,3
        # ---------------------------------------------------------
        amax = target[0]
        bmax = target[1]

        INF = 10**9

        dp = [[INF] * (bmax + 1) for _ in range(amax + 1)]
        dp[0][0] = 0

        useful = [
            (1, 0),  # 2
            (0, 1),  # 3
            (2, 0),  # 4
            (1, 1),  # 6
            (3, 0),  # 8
            (0, 2),  # 9
        ]

        for a in range(amax + 1):
            for b in range(bmax + 1):

                if a == 0 and b == 0:
                    continue

                best = INF

                for da, db in useful:
                    if a >= da and b >= db:
                        best = min(best, dp[a - da][b - db] + 1)

                dp[a][b] = best

        # ---------------------------------------------------------
        # Minimum digits required for a factor requirement
        # ---------------------------------------------------------
        def min_digits(req):
            a, b, c, d = req
            return c + d + dp[a][b]

        # ---------------------------------------------------------
        # Construct lexicographically smallest suffix
        # of exactly k digits satisfying req
        # ---------------------------------------------------------
        def build_suffix(k, req):
            req = list(req)
            ans = []

            while any(req):
                for digit in range(1, 10):

                    f = factors[digit]

                    new_req = [
                        max(0, req[j] - f[j])
                        for j in range(4)
                    ]

                    if min_digits(new_req) <= k - 1:
                        ans.append(str(digit))
                        req = new_req
                        k -= 1
                        break

            ans.extend(['1'] * k)

            return ''.join(ans)

        # ---------------------------------------------------------
        # Check if num itself works
        # ---------------------------------------------------------
        current = [0, 0, 0, 0]
        has_zero = False

        for ch in num:
            d = ord(ch) - 48

            if d == 0:
                has_zero = True
            else:
                f = factors[d]
                for j in range(4):
                    current[j] += f[j]

        if not has_zero:
            if all(current[j] >= target[j] for j in range(4)):
                return num

        # ---------------------------------------------------------
        # Try to find answer with SAME length
        # ---------------------------------------------------------
        prefix = current[:]
        zeros_in_prefix = num.count('0')

        for i in range(n - 1, -1, -1):

            d = ord(num[i]) - 48

            # Remove num[i] from prefix
            if d != 0:
                f = factors[d]
                for j in range(4):
                    prefix[j] -= f[j]

            if d == 0:
                zeros_in_prefix -= 1

            # Prefix before i must be zero-free
            if zeros_in_prefix > 0:
                continue

            # Try smallest digit greater than num[i]
            for new_digit in range(d + 1, 10):

                f = factors[new_digit]

                req = [
                    max(0, target[j] - prefix[j] - f[j])
                    for j in range(4)
                ]

                suffix_len = n - i - 1

                if min_digits(req) <= suffix_len:
                    return (
                        num[:i]
                        + str(new_digit)
                        + build_suffix(suffix_len, req)
                    )

        # ---------------------------------------------------------
        # No same-length answer.
        #
        # Find the MINIMUM feasible length > n.
        # ---------------------------------------------------------
        minimum_required = min_digits(target)

        length = max(n + 1, minimum_required)

        # ---------------------------------------------------------
        # Construct smallest zero-free number of this length
        # satisfying target.
        # ---------------------------------------------------------
        req = target[:]
        ans = []

        for pos in range(length):

            remaining = length - pos - 1

            for digit in range(1, 10):

                f = factors[digit]

                new_req = [
                    max(0, req[j] - f[j])
                    for j in range(4)
                ]

                if min_digits(new_req) <= remaining:
                    ans.append(str(digit))
                    req = new_req
                    break

        return ''.join(ans)