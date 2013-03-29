#!/usr/bin/python

import sys
import csv

# ./calc-avg.py $GROUP $INCREMENT $ITERATIONS
# GROUP is the name
# INCREMENT: the dataset will be incremented by this factor
# ITERATIONS: the dataset will be incremented this many times
def main(argv=None):
	if argv is None:
		argv = sys.argv
	if len(argv) != 4:
		print("Call using: ./calc-avg.py GROUP INCREMENT ITERATIONS")
		return
	
	#group = "3000"
	group = str(argv[1])
	increment = int(argv[2])
	iterations = int(argv[3])
	
	dataset_sizes = map((lambda a: a*increment), range(1, iterations+1))

	trace_names = ["listUsers", "filterWhereUsers", "orderByUsers"]
	
	def gen_logname(index): return group + "i" + str(index).zfill(2)
	lognames = map(gen_logname, range(1, iterations+1))
	iterations = zip(lognames, dataset_sizes)
	#logs = ["3000i01", "3000i02", "3000i03"]
	#logs = ["3000i01"]

	trace_avgs = {}
	# init each value in the map with an empty array
	for trace_name in trace_names:
		trace_avgs[trace_name] = []

	for (index, (logname, dataset_size)) in enumerate(iterations):
		#print (logname, dataset_size)
		for trace_name in trace_names:
			#trace_name = "filterWhereUsers"
			avg = calc_avg_duration(group, logname, trace_name)
			# list of tuples, each tuple contains the iteration name and the avg
			trace_avgs[trace_name].append((logname, dataset_size, avg))
		
		config = {}
		runconfig_location = "archive/"+group+"/"+logname+"/run.config"
		textFile = open(runconfig_location, 'rb')
		# contents:
		# group=3000 log=3000i03 repeat=5 unitsize=1
		for row in textFile:
			#print row
			for keyvalue in row.rstrip().split(" "):
				key = keyvalue.split("=")[0]
				value = keyvalue.split("=")[1]
				config[key] = value
		textFile.close()
		# TODO: use the config the get the actual dataset size
	
	for trace_name in trace_avgs.keys():
		output_location = "archive/"+group+"/avg."+group+"."+trace_name+".txt"
		f = open(output_location,'w')
		f.write('logname\tdatasetsize\tduration\n')
		
		print "Writing averages for " + trace_name
		for (logname, dataset_size, avg) in trace_avgs[trace_name]:
			f.write(str(logname))
			f.write("\t")
			f.write(str(dataset_size))
			f.write("\t")
			f.write(str(avg))
			f.write("\n")
		f.close()
	#output:
	#datasetsize	duration
	#1	12
	#2	15
	#3	20
	#4	25

def calc_avg_duration(group, logname, trace_name):
	location = "archive/"+group+"/"+logname+"/frames/FrameDuration."+logname+"."+trace_name+".trace"
	# contents:
	# filename	location	page-name	starttime	endtime
	# performance01.app	59,1,68,1	orderByUsers	1357995180635	1357995181164

	# list of rows
	lol = list(csv.reader(open(location, 'rb'), delimiter='\t'))
	#print lol
	# average
	def duration(row): return long(row[4])-long(row[3])
	durations = map(duration, lol)
	durations.sort()
	#print durations
	#print durations[1:len(durations)-1] # drop highest and lowest
	avg = int(sum(durations) / float(len(durations)))
	return avg

if __name__ == "__main__":
	main()
