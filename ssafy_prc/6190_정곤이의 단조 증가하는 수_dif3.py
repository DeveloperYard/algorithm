def check_increase(n):
    num = 10
    while n > 0:
        if n%10 <= num:
            num = n%10
            n //= 10
        else:
            return False

    return True


def sol(arr):
    ans = 0
    for i in range(len(arr)-1):
        for j in range(i+1, len(arr)):
            n = arr[i]*arr[j]
            print(f'i : {i} j : {j} n : {n}')
            if check_increase(n):
                if ans < n:
                    ans = n

    return ans


for tc in range(int(input())):
    n = int(input())
    arr = list(map(int, input().split()))
    res = sol(arr)
    ans = "-1" if res == 0 else str(res)

    print(f'#{tc+1} {ans}')