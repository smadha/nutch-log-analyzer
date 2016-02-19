# nutch-log-analyzer

Analysing hadoop.log by grepping error paterns.

Some useful patterns

```
Print URLs with 500 error code  $ grep "500, url" logs/hadoop.lo* | cut -d "," -f 3 | cut -d "=" -f 2
Print all unique URLS $ grep "500, url" logs_bak/hadoop.lo* | cut -d "," -f 3 | cut -d "=" -f 2 | sort | uniq -u		
print sorted 40* error codes $ grep ", url" logs/hadoop.log | cut -d "=" -f2 | cut -d "," -f 1 | grep 40 | sort	403,405	
count of 50* error $ grep ", url" logs/hadoop.log | cut -d "=" -f2 | cut -d "," -f 1 | grep 50 | wc -l	500	
grep all urls out of hadoop.log $ grep -Eo "(http|https)://[a-zA-Z0-9./?=_-]*" logs/hadoop.log		 
```
